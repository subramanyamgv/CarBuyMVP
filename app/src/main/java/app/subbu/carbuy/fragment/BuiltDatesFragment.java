package app.subbu.carbuy.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import app.subbu.carbuy.R;
import app.subbu.carbuy.activity.BuiltDatesActivity;
import app.subbu.carbuy.activity.EntitySelectionListener;
import app.subbu.carbuy.adapter.BuiltDateListAdapter;
import app.subbu.carbuy.entity.BuiltDate;
import app.subbu.carbuy.entity.Manufacturer;
import app.subbu.carbuy.entity.Model;
import app.subbu.carbuy.injector.component.CarSelectionComponent;
import app.subbu.carbuy.utils.ErrorUtils;
import app.subbu.mvp.model.BuiltDates;
import app.subbu.mvp.presenter.BuiltDatesPresenter;
import app.subbu.mvp.view.BuiltDatesView;
import app.subbu.repository.Repository;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Subramanyam on 22-Jan-2017.
 */

public class BuiltDatesFragment extends Fragment implements BuiltDatesView,
        BaseQuickAdapter.RequestLoadMoreListener {

    public static final String TAG = "BuiltDatesFragment";

    @Inject
    BuiltDatesPresenter mPresenter;

    @BindView(R.id.list)
    RecyclerView mRecyclerView;

    private EntitySelectionListener<BuiltDate> selectionListener;
    private BuiltDateListAdapter mAdapter;
    private Manufacturer mManufacturer;
    private Model mModel;
    private int currentPage = 0;

    public static BuiltDatesFragment newInstance() {
        return new BuiltDatesFragment();
    }

    public static void start(int contentViewId, FragmentManager fragmentManager, Manufacturer manufacturer, Model model) {
        BuiltDatesFragment fragment = newInstance();
        fragment.setManufacturer(manufacturer);
        fragment.setModel(model);
        fragmentManager.beginTransaction().replace(contentViewId, fragment,
                TAG).commit();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies();
        mPresenter.onCreate();
    }

    private void injectDependencies() {
        if (getActivity() instanceof BuiltDatesActivity) {
            BuiltDatesActivity activity = (BuiltDatesActivity) getActivity();
            CarSelectionComponent carSelectionComponent = activity.getCarSelectionComponent();
            carSelectionComponent.inject(this);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            selectionListener = (EntitySelectionListener)getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + " should implement EntitySelectionListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_car_types, container, false);
        ButterKnife.bind(this, view);

        //Init Adapter
        mAdapter = new BuiltDateListAdapter(new LinkedList<BuiltDate>());

        //Init List
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);

        //Init Presenter
        mPresenter.attachView(this);

        //Set Load more call back
        mAdapter.setOnLoadMoreListener(this);

        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                BuiltDate builtDate = (BuiltDate) adapter.getItem(position);
                selectionListener.onEntitySelected(builtDate);
            }
        });
		
		mPresenter.getBuiltDates(mModel.getModelId(), mManufacturer.getManufacturerId(),
                0, Repository.DEFAULT_PAGE_SIZE);

        return view;
    }

    @Override
    public void onLoadMoreRequested() {
        mPresenter.getBuiltDates(mModel.getModelId(), mManufacturer.getManufacturerId(),
                currentPage + 1, Repository.DEFAULT_PAGE_SIZE);
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mPresenter.onStop();
    }

    public void addBuiltDates(BuiltDates builtDates) {

        List<BuiltDate> data = new LinkedList<>();
        int itemType = 0;

        for (Map.Entry<String, String> entry : builtDates.getWkda().entrySet()) {
            BuiltDate builtDate = new BuiltDate(itemType, entry.getKey());
            data.add(builtDate);
            itemType = itemType ^ 1;
        }

        currentPage = builtDates.getPage();
        mAdapter.addData(data);

        if (currentPage == builtDates.getTotalPageCount())
            mAdapter.loadMoreEnd(true);
        else
            mAdapter.loadMoreComplete();
    }

    @Override
    public void showLoading() {

        if (!isAdded())
            return;

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mAdapter.setEmptyView(R.layout.loading_view,
                        (ViewGroup) mRecyclerView.getParent());
            }
        };

        getActivity().runOnUiThread(runnable);
    }

    @Override
    public void showEmpty() {

        if (!isAdded())
            return;

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mAdapter.setEmptyView(R.layout.empty_view,
                        (ViewGroup) mRecyclerView.getParent());
            }
        };

        getActivity().runOnUiThread(runnable);
    }

    @Override
    public void showBuiltDates(final BuiltDates builtDates) {

        if (!isAdded())
            return;

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                addBuiltDates(builtDates);
            }
        };

        getActivity().runOnUiThread(runnable);
    }

    @Override
    public void showError(Throwable e) {

        if (!isAdded())
            return;

        final ErrorUtils.ErrorType error = ErrorUtils.getErrorType(e);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                mAdapter.setEmptyView(R.layout.error_view,
                        (ViewGroup) mRecyclerView.getParent());

                ((TextView)mAdapter.getEmptyView()
                        .findViewById(R.id.error_text))
                        .setText(error.getRes());
            }
        };

        getActivity().runOnUiThread(runnable);
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.mManufacturer = manufacturer;
    }

    public void setModel(Model model) {
        this.mModel = model;
    }
}
